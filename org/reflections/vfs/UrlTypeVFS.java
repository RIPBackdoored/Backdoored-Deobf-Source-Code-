package org.reflections.vfs;

import com.google.common.base.*;
import java.util.jar.*;
import java.io.*;
import java.net.*;
import org.reflections.*;
import java.util.regex.*;

public class UrlTypeVFS implements Vfs.UrlType
{
    public static final String[] REPLACE_EXTENSION;
    final String VFSZIP = "vfszip";
    final String VFSFILE = "vfsfile";
    Predicate<java.io.File> realFile;
    
    public UrlTypeVFS() {
        super();
        this.realFile = new Predicate<java.io.File>() {
            final /* synthetic */ UrlTypeVFS this$0;
            
            UrlTypeVFS$1() {
                this.this$0 = this$0;
                super();
            }
            
            @Override
            public boolean apply(final java.io.File file) {
                return file.exists() && file.isFile();
            }
            
            @Override
            public /* bridge */ boolean apply(final Object o) {
                return this.apply((java.io.File)o);
            }
        };
    }
    
    @Override
    public boolean matches(final URL url) {
        return "vfszip".equals(url.getProtocol()) || "vfsfile".equals(url.getProtocol());
    }
    
    @Override
    public Vfs.Dir createDir(final URL url) {
        try {
            final URL adaptedUrl = this.adaptURL(url);
            return new ZipDir(new JarFile(adaptedUrl.getFile()));
        }
        catch (Exception e) {
            try {
                return new ZipDir(new JarFile(url.getFile()));
            }
            catch (IOException e2) {
                if (Reflections.log != null) {
                    Reflections.log.warn("Could not get URL", e);
                    Reflections.log.warn("Could not get URL", e2);
                }
                return null;
            }
        }
    }
    
    public URL adaptURL(final URL url) throws MalformedURLException {
        if ("vfszip".equals(url.getProtocol())) {
            return this.replaceZipSeparators(url.getPath(), this.realFile);
        }
        if ("vfsfile".equals(url.getProtocol())) {
            return new URL(url.toString().replace("vfsfile", "file"));
        }
        return url;
    }
    
    URL replaceZipSeparators(final String path, final Predicate<java.io.File> acceptFile) throws MalformedURLException {
        int pos = 0;
        while (pos != -1) {
            pos = this.findFirstMatchOfDeployableExtention(path, pos);
            if (pos > 0) {
                final java.io.File file = new java.io.File(path.substring(0, pos - 1));
                if (acceptFile.apply(file)) {
                    return this.replaceZipSeparatorStartingFrom(path, pos);
                }
                continue;
            }
        }
        throw new ReflectionsException("Unable to identify the real zip file in path '" + path + "'.");
    }
    
    int findFirstMatchOfDeployableExtention(final String path, final int pos) {
        final Pattern p = Pattern.compile("\\.[ejprw]ar/");
        final Matcher m = p.matcher(path);
        if (m.find(pos)) {
            return m.end();
        }
        return -1;
    }
    
    URL replaceZipSeparatorStartingFrom(final String path, final int pos) throws MalformedURLException {
        final String zipFile = path.substring(0, pos - 1);
        String zipPath = path.substring(pos);
        int numSubs = 1;
        for (final String ext : UrlTypeVFS.REPLACE_EXTENSION) {
            while (zipPath.contains(ext)) {
                zipPath = zipPath.replace(ext, ext.substring(0, 4) + "!");
                ++numSubs;
            }
        }
        String prefix = "";
        for (int i = 0; i < numSubs; ++i) {
            prefix += "zip:";
        }
        if (zipPath.trim().length() == 0) {
            return new URL(prefix + "/" + zipFile);
        }
        return new URL(prefix + "/" + zipFile + "!" + zipPath);
    }
    
    static {
        REPLACE_EXTENSION = new String[] { ".ear/", ".jar/", ".war/", ".sar/", ".har/", ".par/" };
    }
}
