package model.item;

public class ItemDTO {
	private int itemNo;
	private String itemCategory;
	private String itemName;
	private int itemPrice;

	private int itemStockGarage;
	private int itemStockDisplay;
	private int itemStockStock;
	private String ItemImg;
	
	
	private int itemOrderAmount;
	
	public int getItemOrderAmount() {
		return itemOrderAmount;
	}
	public void setItemOrderAmount(int itemOrderAmount) {
		this.itemOrderAmount = itemOrderAmount;
	}
	public ItemDTO() {
		
	}
	public ItemDTO(String itemName, int itemPrice, int itemOrderAmount) {
		super();
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemOrderAmount = itemOrderAmount;
	}
	
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	public int getItemStockGarage() {
		return itemStockGarage;
	}
	public void setItemStockGarage(int itemStockGarage) {
		this.itemStockGarage = itemStockGarage;
	}
	public int getItemStockDisplay() {
		return itemStockDisplay;
	}
	public void setItemStockDisplay(int itemStockDisplay) {
		this.itemStockDisplay = itemStockDisplay;
	}
	public int getItemStockStock() {
		return itemStockStock;
	}
	public void setItemStockStock(int itemStockStock) {
		this.itemStockStock = itemStockStock;
	}
	public String getItemImg() {
		return ItemImg;
	}
	public void setItemImg(String itemImg) {
		ItemImg = itemImg;
	}
}
