package com.collinriggs.laserfactory.blocks.lasers.generic;

import javax.annotation.Nullable;

import com.collinriggs.laserfactory.blocks.BlockRotatable;
import com.collinriggs.laserfactory.blocks.TEHelper;

import net.minecraft.block.Block;
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

public abstract class TileEntityLaser extends TileEntity implements ITickable {
	
	public final int MAX_RANGE, MIN_RANGE;
	
	private int laserLength = 0;
	
	private BlockPos targetPos;
	
	public TileEntityLaser(int maxRange, int minRange) {
		MAX_RANGE = maxRange;
		MIN_RANGE = minRange;
	}
	
	public TileEntityLaser(int maxRange) {
		this(maxRange, 1);
	}

	@Override
	public void update() {
		if (this.getWorld() != null && !this.getWorld().isRemote) {
			if (targetPos == null) {
				//if there is no block to check for or the old block has been replaced
				checkForBlock();
			} else if (!laserEndAtBlock(this.getWorld().getBlockState(targetPos).getBlock()))
				checkForBlock();
		}
	}
	
	private void checkForBlock() {
		EnumFacing facing = (EnumFacing) world.getBlockState(this.getPos()).getProperties().get(BlockRotatable.FACING);
		
		switch(facing) {
		case DOWN:
			for (int y = MIN_RANGE; y <= MAX_RANGE; y++) {
				BlockPos pos = new BlockPos(this.getPos().getX(), this.getPos().getY() - y, this.getPos().getZ());
				IBlockState state = this.getWorld().getBlockState(pos);
				if (state.isFullBlock()) {
					if (laserEndAtBlock(state.getBlock())) {
						laserLength = y;
					} else {
						laserLength = 0;
					}
					this.markDirty();
					TEHelper.sync(this);
					this.targetPos = pos;
				}
				if (y >= MAX_RANGE) {
					laserLength = 0;
					this.markDirty();
					TEHelper.sync(this);
					this.targetPos = pos;
				}
			}
			break;
		case EAST:
			for (int x = MIN_RANGE; x <= MAX_RANGE; x++) {
				BlockPos pos = new BlockPos(this.getPos().getX() + x, this.getPos().getY(), this.getPos().getZ());
				IBlockState state = this.getWorld().getBlockState(pos);
				if (state.isFullBlock()) {
					if (laserEndAtBlock(state.getBlock())) {
						laserLength = x;
					} else {
						laserLength = 0;
					}
					this.markDirty();
					TEHelper.sync(this);
					this.targetPos = pos;
				}
				if (x >= MAX_RANGE) {
					laserLength = 0;
					this.markDirty();
					TEHelper.sync(this);
					this.targetPos = pos;
				}
			}
			break;
		case NORTH:
			for (int z = MIN_RANGE; z <= MAX_RANGE; z++) {
				BlockPos pos = new BlockPos(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ() - z);
				IBlockState state = this.getWorld().getBlockState(pos);
				if (state.isFullBlock()) {
					if (laserEndAtBlock(state.getBlock())) {
						laserLength = z;
					} else {
						laserLength = 0;
					}
					this.markDirty();
					TEHelper.sync(this);
					this.targetPos = pos;
				}
				if (z >= MAX_RANGE) {
					laserLength = 0;
					this.markDirty();
					TEHelper.sync(this);
					this.targetPos = pos;
				}
			}
			break;
		case SOUTH:
			for (int z = MIN_RANGE; z <= MAX_RANGE; z++) {
				BlockPos pos = new BlockPos(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ() + z);
				IBlockState state = this.getWorld().getBlockState(pos);
				if (state.isFullBlock()) {
					if (laserEndAtBlock(state.getBlock())) {
						laserLength = z;
					} else {
						laserLength = 0;
					}
					this.markDirty();
					TEHelper.sync(this);
					this.targetPos = pos;
				}
				if (z >= MAX_RANGE) {
					laserLength = 0;
					this.markDirty();
					TEHelper.sync(this);
					this.targetPos = pos;
				}
			}
			break;
		case UP:
			for (int y = MIN_RANGE; y <= MAX_RANGE; y++) {
				BlockPos pos = new BlockPos(this.getPos().getX(), this.getPos().getY() + y, this.getPos().getZ());
				IBlockState state = this.getWorld().getBlockState(pos);
				if (state.isFullBlock()) {
					if (laserEndAtBlock(state.getBlock())) {
						laserLength = y;
					} else {
						laserLength = 0;
					}
					this.markDirty();
					TEHelper.sync(this);
					this.targetPos = pos;
				}
				if (y >= MAX_RANGE) {
					laserLength = 0;
					this.markDirty();
					TEHelper.sync(this);
					this.targetPos = pos;
				}
			}
			break;
		case WEST:
			for (int x = MIN_RANGE; x <= MAX_RANGE; x++) {
				BlockPos pos = new BlockPos(this.getPos().getX() - x, this.getPos().getY(), this.getPos().getZ());
				IBlockState state = this.getWorld().getBlockState(pos);
				if (state.isFullBlock()) {
					if (laserEndAtBlock(state.getBlock())) {
						laserLength = x;
					} else {
						laserLength = 0;
					}
					this.markDirty();
					TEHelper.sync(this);
					this.targetPos = pos;
				}
				if (x >= MAX_RANGE) {
					laserLength = 0;
					this.markDirty();
					TEHelper.sync(this);
					this.targetPos = pos;
				}
			}
			break;			
		}
	}
	
	protected abstract boolean laserEndAtBlock(Block block);
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		
		this.laserLength = compound.getInteger("LaserLength");
		
		this.markDirty();
		TEHelper.sync(this);
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

	@Override
    public NBTTagCompound getUpdateTag() {
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
