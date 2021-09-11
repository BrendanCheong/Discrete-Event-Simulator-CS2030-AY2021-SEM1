class RubikRight extends RubikFront {

    public RubikRight(Rubik rubik) {
        super(rubik.getGrid());
    }

    @Override
    public RubikFront left() {
        return super.rightView().left().leftView();
    }

    @Override
    public RubikFront right() {
        return super.rightView().right().leftView();
    }

    @Override
    public RubikFront half() {
        return super.rightView().half().leftView();
    }

}
