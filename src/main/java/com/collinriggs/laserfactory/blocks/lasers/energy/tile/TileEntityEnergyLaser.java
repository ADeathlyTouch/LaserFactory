package com.collinriggs.laserfactory.blocks.lasers.energy.tile;

import javax.annotation.Nullable;

import com.collinriggs.laserfactory.blocks.BlockRotatable;

import net.minecraft.block.BlockGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class TileEntityEnergyLaser extends TileEntity implements ITickable {
	
	public final int ENERGY_TRANSFER_RATE;
	public final int MAX_RANGE;
	
	private int laserLength = 0;
	
	protected TileEntityEnergyLaser(int transferRate, int maxRange) {
		ENERGY_TRANSFER_RATE = transferRate;
		MAX_RANGE = maxRange;
	}

	@Override
	public void update() {
		if (this.getWorld() != null) {
			EnumFacing facing = (EnumFacing) world.getBlockState(this.getPos()).getProperties().get(BlockRotatable.FACING);
			
			switch(facing) {
			case DOWN:
				for (int y = 1; y <= MAX_RANGE; y++) {
					BlockPos pos = new BlockPos(this.getPos().getX(), this.getPos().getY() - y, this.getPos().getZ());
					IBlockState state = this.getWorld().getBlockState(pos);
					if (state.isFullBlock()) {
						if (state.getBlock() instanceof BlockGrass) {
							laserLength = y;
						} else {
							laserLength = 0;
						}
						this.markDirty();
						break;
					}
					if (y >= MAX_RANGE) {
						laserLength = 0;
						this.markDirty();
					}
				}
				break;
			case EAST:
				for (int x = 1; x <= MAX_RANGE; x++) {
					BlockPos pos = new BlockPos(this.getPos().getX() + x, this.getPos().getY(), this.getPos().getZ());
					IBlockState state = this.getWorld().getBlockState(pos);
					if (state.isFullBlock()) {
						if (state.getBlock() instanceof BlockGrass) {
							laserLength = x;
						} else {
							laserLength = 0;
						}
						this.markDirty();
						break;
					}
					if (x >= MAX_RANGE) {
						laserLength = 0;
						this.markDirty();
					}
				}
				break;
			case NORTH:
				for (int z = 1; z <= MAX_RANGE; z++) {
					BlockPos pos = new BlockPos(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ() - z);
					IBlockState state = this.getWorld().getBlockState(pos);
					if (state.isFullBlock()) {
						if (state.getBlock() instanceof BlockGrass) {
							laserLength = z;
						} else {
							laserLength = 0;
						}
						this.markDirty();
						break;
					}
					if (z >= MAX_RANGE) {
						laserLength = 0;
						this.markDirty();
					}
				}
				break;
			case SOUTH:
				for (int z = 1; z <= MAX_RANGE; z++) {
					BlockPos pos = new BlockPos(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ() + z);
					IBlockState state = this.getWorld().getBlockState(pos);
					if (state.isFullBlock()) {
						if (state.getBlock() instanceof BlockGrass) {
							laserLength = z;
						} else {
							laserLength = 0;
						}
						this.markDirty();
						break;
					}
					if (z >= MAX_RANGE) {
						laserLength = 0;
						this.markDirty();
					}
				}
				break;
			case UP:
				for (int y = 1; y <= MAX_RANGE; y++) {
					BlockPos pos = new BlockPos(this.getPos().getX(), this.getPos().getY() + y, this.getPos().getZ());
					IBlockState state = this.getWorld().getBlockState(pos);
					if (state.isFullBlock()) {
						if (state.getBlock() instanceof BlockGrass) {
							laserLength = y;
						} else {
							laserLength = 0;
						}
						this.markDirty();
						break;
					}
					if (y >= MAX_RANGE) {
						laserLength = 0;
						this.markDirty();
					}
				}
				break;
			case WEST:
				for (int x = 1; x <= MAX_RANGE; x++) {
					BlockPos pos = new BlockPos(this.getPos().getX() - x, this.getPos().getY(), this.getPos().getZ());
					IBlockState state = this.getWorld().getBlockState(pos);
					if (state.isFullBlock()) {
						if (state.getBlock() instanceof BlockGrass) {
							laserLength = x;
						} else {
							laserLength = 0;
						}
						this.markDirty();
						break;
					}
					if (x >= MAX_RANGE) {
						laserLength = 0;
						this.markDirty();
					}
				}
				break;			
			}
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		
		this.laserLength = compound.getInteger("LaserLength");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		
		compound.setInteger("LaserLength", laserLength);
		
		return compound;
	}
	
	@Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
    }
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.getNbtCompound());
	}

    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }
	
	public int getLaserLength() {
		return laserLength;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared() {
        return 65536.0D;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }
	
}
