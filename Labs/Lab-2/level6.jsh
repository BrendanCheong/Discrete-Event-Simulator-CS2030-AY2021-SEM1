/**
 * Checks if the current indexed Loader in loader array is the third loader.
 * @param index takes in current index to be checked in the array
 * @return whether its the third item in the array or not
 */
public boolean checkThirdLoader(int index) {
    int THIRD_INDEX = 3;
    int OFFSET = 1;
    return ((index + OFFSET) % THIRD_INDEX) == 0;
  }

public void serveCruises(Cruise[] cruises) {
    Loader[] loaders = new Loader[40];

    for (Cruise cruise : cruises) {
        int currentLoadersRequired = cruise.getNumOfLoadersRequired();

        for (int i = 0 ; i < loaders.length; i++) {
            if (currentLoadersRequired <= 0) {
                break;
            } else if (loaders[i] == null) {
                if (checkThirdLoader(i)) {
                    loaders[i] = new RecycledLoader(i + 1, cruise);
                } else {
                    loaders[i] = new Loader(i + 1, cruise);
                }
                System.out.println(loaders[i].toString());
                currentLoadersRequired -= 1;
            } else if (loaders[i].canServe(cruise)) {
                loaders[i] = loaders[i].serve(cruise);
                System.out.println(loaders[i].toString());
                currentLoadersRequired -= 1;
            }
        }
    }
}
