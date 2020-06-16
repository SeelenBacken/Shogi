public class TurnBean {
	private PositionBean relTo;

	public TurnBean() {
		relTo = new PositionBean();
	}

	public PositionBean getTo() {
		return relTo;
	}

	public void setTo(PositionBean to) {
		this.relTo = to;
	}
}
