class RubikBack extends RubikFront {

    public RubikBack(Rubik rubik) {
        super(rubik.getGrid());
    }

    @Override
    public RubikFront left() {
        return super.backView().left().backView();
    }

    @Override
    public RubikFront right() {
        return super.backView().right().backView();
    }

    @Override
    public RubikFront half() {
        return super.backView().half().backView();
    }

}
