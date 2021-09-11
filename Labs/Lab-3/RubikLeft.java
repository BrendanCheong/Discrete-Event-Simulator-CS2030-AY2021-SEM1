class RubikLeft extends RubikFront {

    public RubikLeft(Rubik rubik) {
        super(rubik.getGrid());
    }

    @Override
    public RubikFront left() {
        return super.leftView().left().rightView();
    }

    @Override
    public RubikFront right() {
        return super.leftView().right().rightView();
    }

    @Override
    public RubikFront half() {
        return super.leftView().half().rightView();
    }

}
