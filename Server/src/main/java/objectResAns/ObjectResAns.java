package objectResAns;

import java.io.Serializable;

public class ObjectResAns implements Serializable {
    private String resTesxt;
    private boolean resAns;

    public ObjectResAns(String resTesxt, boolean resAns) {
        this.resTesxt = resTesxt;
        this.resAns = resAns;
    }

    public String getResTesxt() {
        return resTesxt;
    }

    public boolean isResAns() {
        return resAns;
    }

    public void setResTesxt(String resTesxt) {
        this.resTesxt = resTesxt;
    }

    public void setResAns(boolean resAns) {
        this.resAns = resAns;
    }
}
