package filters;

/*
  This abstract class allows its child filter classes to wrap each other and use the appropriate
  filtering logic so as to take into consideration the state of all filters wrapping each other.
 */
public abstract class FilterBooleanLogic extends StatefulFilterInputStream {
    protected StatefulFilterInputStream filterInputStream;

    public FilterBooleanLogic(StatefulFilterInputStream filterInputStream) {
        super(filterInputStream.reader);
        this.filterInputStream = filterInputStream;
    }

    @Override
    public boolean isAccepting() {
        return isAccepting && filterInputStream.isAccepting();
    }
}
