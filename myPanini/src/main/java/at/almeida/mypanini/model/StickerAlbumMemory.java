package at.almeida.mypanini.model;

/**
 * Class that holds the sticker album information in memory
 * @author miguel
 *
 */
public class StickerAlbumMemory {
	
	private long albumId;
	private String[] stickers;
	private Long[] stickersId;
	private Integer[] stickersCount;
	
	
	public StickerAlbumMemory(int size) {
		stickers = new String[size];
		stickersId = new Long[size];
		stickersCount = new Integer[size];
	}
	public long getAlbumId() {
		return albumId;
	}
	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}
	public void setStickers(String[] stickers) {
		this.stickers = stickers;
	}
	public void setStickersId(Long[] stickersId) {
		this.stickersId = stickersId;
	}
	public void setStickersCount(Integer[] stickersCount) {
		this.stickersCount = stickersCount;
	}
	
	public String getStickerAtPosition(int position){
		return stickers[position];
	}

	public Long getStickerIdAtPosition(int position){
		return stickersId[position];
	}
	
	public Integer getStickerCountAtPosition(int position){
		return stickersCount[position];
	}
	
	public void setStickerCountAtPosition(int position,int count){
		stickersCount[position] = count;
	}
	
	public void setStickerAtPosition(int position,String sticker){
		stickers[position] = sticker;
	}
	
	public void setStickerIdAtPosition(int position,long id){
		stickersId[position] = id;
	}
}
