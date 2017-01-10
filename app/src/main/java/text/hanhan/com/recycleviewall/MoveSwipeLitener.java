package text.hanhan.com.recycleviewall;

/**
 * Created by 韩永光
 * on 2017/1/10 15:43.
 */
public interface MoveSwipeLitener {
    void  onMove(int oldadapterPosition, int newadapterPosition);
    void Swiped(int adapterPosition);
}
