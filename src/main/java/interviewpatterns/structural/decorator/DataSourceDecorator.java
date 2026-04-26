package interviewpatterns.structural.decorator;

public abstract class DataSourceDecorator implements DataSource {
    protected final DataSource wrapped;

    protected DataSourceDecorator(DataSource wrapped) {
        this.wrapped = wrapped;
    }
}
