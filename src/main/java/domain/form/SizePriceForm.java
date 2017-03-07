package domain.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

public class SizePriceForm {
	
	private Double priceS;
	private Double priceM;
	private Double priceL;
	private Double priceXL;
	private boolean S;
	private boolean M;
	private boolean L;
	private boolean XL;
	private int routeId;
	
	@Min(0)
	@Digits(integer=9,fraction=2)
	public Double getPriceS() {
		return priceS;
	}
	public void setPriceS(Double priceS) {
		this.priceS = priceS;
	}
	
	@Min(0)
	@Digits(integer=9,fraction=2)
	public Double getPriceM() {
		return priceM;
	}
	public void setPriceM(Double priceM) {
		this.priceM = priceM;
	}
	
	@Min(0)
	@Digits(integer=9,fraction=2)
	public Double getPriceL() {
		return priceL;
	}
	public void setPriceL(Double priceL) {
		this.priceL = priceL;
	}
	
	@Min(0)
	@Digits(integer=9,fraction=2)
	public Double getPriceXL() {
		return priceXL;
	}
	public void setPriceXL(Double priceXL) {
		this.priceXL = priceXL;
	}
	
	
	public boolean isS() {
		return S;
	}
	public void setS(boolean s) {
		S = s;
	}
	
	
	public boolean isM() {
		return M;
	}
	public void setM(boolean m) {
		M = m;
	}
	
	
	public boolean isL() {
		return L;
	}
	public void setL(boolean l) {
		L = l;
	}
	
	
	public boolean isXL() {
		return XL;
	}
	public void setXL(boolean xL) {
		XL = xL;
	}
	
	@Min(0)
	public int getRouteId() {
		return routeId;
	}
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}
	
	
}
