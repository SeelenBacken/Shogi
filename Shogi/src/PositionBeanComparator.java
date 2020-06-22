import java.util.Comparator;

public class PositionBeanComparator implements Comparator<PositionBean> {

	@Override
	public int compare(PositionBean arg0, PositionBean arg1) {
		return (int) Math.sqrt(Math.pow(arg0.getX() - arg1.getX(), 2) + Math.pow(arg0.getY() - arg1.getY(), 2));
	}

}
