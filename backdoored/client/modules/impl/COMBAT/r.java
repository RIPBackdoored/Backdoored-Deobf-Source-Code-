package f.b.o.g.x;

import f.b.o.g.*;
import net.minecraft.entity.player.*;
import net.minecraft.block.material.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import f.b.q.*;
import java.util.*;

@c$b(name = "Hole Filler", description = "Fill holes that enemies could jump into", category = f.b.o.c.c.COMBAT)
public class r extends c
{
    private f.b.f.c pw;
    private f.b.f.c pu;
    
    public r() {
        super();
        this.pw = new f.b.f.c("Radius", this, 3, 1, 5);
        this.pu = new f.b.f.c("Range", this, 5, 1, 10);
    }
    
    public void bh() {
        if (this.bu()) {
            for (final EntityPlayer v : r.mc.world.playerEntities) {
                if (!v.getUniqueID().equals(r.mc.player.getUniqueID())) {
                    final int v2 = this.pw.cp();
                    final BlockPos v3 = v.getPosition();
                    final Iterable<BlockPos> v4 = (Iterable<BlockPos>)BlockPos.getAllInBox(v3.add(-v2, -v2, -v2), v3.add(v2, v2, v2));
                    for (final BlockPos v5 : v4) {
                        if (r.mc.player.getDistanceSqToCenter(v5) > this.pu.cp()) {
                            continue;
                        }
                        if (!r.mc.world.getBlockState(v5).getMaterial().isReplaceable() || !r.mc.world.getBlockState(v5.add(0, 1, 0)).getMaterial().isReplaceable()) {
                            continue;
                        }
                        final boolean v6 = r.mc.world.getBlockState(v5.add(0, -1, 0)).getMaterial().isSolid() && r.mc.world.getBlockState(v5.add(1, 0, 0)).getMaterial().isSolid() && r.mc.world.getBlockState(v5.add(0, 0, 1)).getMaterial().isSolid() && r.mc.world.getBlockState(v5.add(-1, 0, 0)).getMaterial().isSolid() && r.mc.world.getBlockState(v5.add(0, 0, -1)).getMaterial().isSolid() && r.mc.world.getBlockState(v5.add(0, 0, 0)).getMaterial() == Material.AIR && r.mc.world.getBlockState(v5.add(0, 1, 0)).getMaterial() == Material.AIR && r.mc.world.getBlockState(v5.add(0, 2, 0)).getMaterial() == Material.AIR;
                        if (!v6 || !r.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(v5)).isEmpty()) {
                            continue;
                        }
                        final int v7 = n.b(Blocks.OBSIDIAN);
                        if (v7 == -1) {
                            continue;
                        }
                        final int v8 = r.mc.player.inventory.currentItem;
                        r.mc.player.inventory.currentItem = v7;
                        n.c(v5);
                        r.mc.player.inventory.currentItem = v8;
                    }
                }
            }
        }
    }
}
