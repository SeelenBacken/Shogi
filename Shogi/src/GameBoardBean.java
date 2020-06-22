import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.TreeMap;

public class GameBoardBean {

	private PositionBean blackKing;
	private PositionBean whiteKing;
	private FigureBean[][] board;
	private ArrayList<FigureBean> blackPrison;
	private ArrayList<FigureBean> whitePrison;
	private TreeMap<PositionBean, ArrayList<TurnBean>> possibleTurns;

	public GameBoardBean() {
		blackKing = getKingBean(TeamType.BLACK);
		whiteKing = getKingBean(TeamType.WHITE);
		board = new FigureBean[9][9];
		blackPrison = new ArrayList<FigureBean>(20);
		whitePrison = new ArrayList<FigureBean>(20);
		possibleTurns = new TreeMap<PositionBean, ArrayList<TurnBean>>(new PositionBeanComparator());
	}

	public FigureBean[][] getBoard() {
		return board;
	}

	public ArrayList<FigureBean> getBlackPrison() {
		return blackPrison;
	}

	public ArrayList<FigureBean> getWhitePrison() {
		return whitePrison;
	}

	public void setBoard(FigureBean[][] board) {
		this.board = board;
	}

	public void setBlackPrison(ArrayList<FigureBean> blackPrison) {
		this.blackPrison = blackPrison;
	}

	public void setWhitePrison(ArrayList<FigureBean> whitePrison) {
		this.whitePrison = whitePrison;
	}

	private PositionBean getKingBean(TeamType team) {
		PositionBean kingPos = new PositionBean();
		switch (team) {
		case BLACK:
			kingPos.setX(4);
			kingPos.setY(8);
			break;
		case WHITE:
			kingPos.setX(4);
			kingPos.setY(0);
			break;
		default:
			throw new InvalidParameterException();
		}
		return kingPos;
	}
}
