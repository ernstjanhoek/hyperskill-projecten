package coffeemachine;

class ResourceException extends Exception {
    String name;

    ResourceException(String name) {
        this.name = name;
    }

    @Override
    public String getMessage() {
        return "Sorry, not enough " + this.name + "!";
    }
}
