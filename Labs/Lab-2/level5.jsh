import static java.util.Objects.isNull;

public void serveCruises(Cruise[] cruises) {
    Loader[] loaders = new Loader[35];

    for (Cruise cruise : cruises) {
        int currentLoadersRequired = cruise.getNumOfLoadersRequired();

        for (int i = 0 ; i < loaders.length; i++) {
            if (currentLoadersRequired <= 0) {
                break;
            } else if (isNull(loaders[i])) {
                loaders[i] = new Loader(i + 1, cruise);
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
