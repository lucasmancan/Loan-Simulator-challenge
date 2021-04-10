package encargo.repositories;


import encargo.EncargoEntity;

import java.util.List;

public interface EncargoRepository {
    List<EncargoEntity> buscarTaxasAtivas();
}
