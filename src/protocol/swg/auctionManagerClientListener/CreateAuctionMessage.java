package protocol.swg.auctionManagerClientListener;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import org.apache.mina.core.buffer.IoBuffer;

import protocol.swg.SWGMessage;

public class CreateAuctionMessage extends SWGMessage {

	private long objectId;
	private long vendorId;
	private int price;
	private int duration;
	private String description;
	private byte premium;

	@Override
	public void deserialize(IoBuffer data) {
		data.skip(6);
		setObjectId(data.getLong());
		setVendorId(data.getLong());
		setPrice(data.getInt());
		setDuration(data.getInt()); // in minutes
		int size = data.getInt();
		try {
			setDescription(new String(ByteBuffer.allocate(size * 2).put(data.array(), data.position(), size * 2).array(), "UTF-16LE"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		data.position(data.position() + size * 2);
		setPremium(data.get());
	}

	@Override
	public IoBuffer serialize() {
		// TODO Auto-generated method stub
		return null;
	}

	public long getObjectId() {
		return objectId;
	}

	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}

	public long getVendorId() {
		return vendorId;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getPremium() {
		return premium;
	}

	public void setPremium(byte premium) {
		this.premium = premium;
	}

}
