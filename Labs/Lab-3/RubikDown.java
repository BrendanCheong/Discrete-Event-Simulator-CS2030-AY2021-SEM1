class RubikDown extends RubikFront {

    public RubikDown(Rubik rubik) {
        super(rubik.getGrid());
    }

    @Override
    public RubikFront left() {
        return super.downView().left().upView();
    }

    @Override
    public RubikFront right() {
        return super.downView().right().upView();
    }

    @Override
    public RubikFront half() {
        return super.downView().half().upView();
    }

}
