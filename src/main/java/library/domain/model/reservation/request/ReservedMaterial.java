package library.domain.model.reservation.request;

import library.domain.model.material.Material;
import library.domain.model.material.MaterialNumber;

/**
 * 予約図書
 */
public class ReservedMaterial {
    Material material;

    public ReservedMaterial(Material material) {
        this.material = material;
    }

    @Deprecated
    ReservedMaterial() {
    }

    public Material material() {
        return material;
    }

    public boolean isA(MaterialNumber materialNumber) {
        return material.materialNumber().sameValue(materialNumber);
    }

    @Override
    public String toString() {
        return "ReservedMaterial{" +
                "material=" + material +
                '}';
    }
}
