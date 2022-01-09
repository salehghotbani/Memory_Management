public class ProgramInfoClass {
    private int PId, PBase, PLimit;

    public ProgramInfoClass(int PId, int PBase, int PLimit) {
        this.PId = PId;
        this.PBase = PBase;
        this.PLimit = PLimit;
    }

    public int getPId() {
        return PId;
    }

    public int getPBase() {
        return PBase;
    }

    public int getPLimit() {
        return PLimit;
    }

    public void setPId(int PId) {
        this.PId = PId;
    }

    public void setPBase(int PBase) {
        this.PBase = PBase;
    }

    public void setPLimit(int PLimit) {
        this.PLimit = PLimit;
    }
}
