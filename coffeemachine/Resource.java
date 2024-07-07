package coffeemachine;

class Resource {
    private int value;
    private String name;

    public int getValue() {
        return value;
    }

    public void checkResource(int limit) throws ResourceException {
        if (limit > this.value) {
            throw new ResourceException(this.name);
        }
    }

    Resource(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public void fill(int value) {
        this.value = this.value + value;
    }

    public void take() {
        this.value = 0;
    }
}
