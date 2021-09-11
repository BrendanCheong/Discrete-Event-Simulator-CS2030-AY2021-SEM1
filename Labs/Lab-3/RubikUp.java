class RubikUp extends RubikFront {

    public RubikUp(Rubik rubik) {
        super(rubik.getGrid());
    }

    @Override
    public RubikFront left() {
        return super.upView().left().downView();
    }

    @Override
    public RubikFront right() {
        return super.upView().right().downView();
    }

    @Override
    public RubikFront half() {
        return super.upView().half().downView();
    }

}
