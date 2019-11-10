package org.spongepowered.asm.obfuscation.mapping;

public enum Type
{
    FIELD, 
    METHOD, 
    CLASS, 
    PACKAGE;
    
    private static final /* synthetic */ Type[] $VALUES;
    
    public static Type[] values() {
        return Type.$VALUES.clone();
    }
    
    public static Type valueOf(final String name) {
        return Enum.valueOf(Type.class, name);
    }
    
    static {
        $VALUES = new Type[] { Type.FIELD, Type.METHOD, Type.CLASS, Type.PACKAGE };
    }
}
