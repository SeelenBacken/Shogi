public class FigureBean {
	private FigureType type;
	private FigureType changeType;
	private TeamType team;

	public FigureBean() {
		type = FigureType.UNDEFINED;
		changeType = FigureType.UNDEFINED;
		team = TeamType.NONE;
	}

	public FigureType getType() {
		return type;
	}

	public void setType(FigureType type) {
		this.type = type;
	}

	public FigureType getChangeType() {
		FigureType tmp = type;
		type = changeType;
		changeType = tmp;

		return type;
	}

	public TeamType getTeam() {
		return team;
	}

	public void setTeam(TeamType team) {
		this.team = team;
	}
}
