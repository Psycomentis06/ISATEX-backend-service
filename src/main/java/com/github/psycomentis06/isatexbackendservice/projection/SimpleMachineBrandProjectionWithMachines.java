package com.github.psycomentis06.isatexbackendservice.projection;

import java.util.List;

public interface SimpleMachineBrandProjectionWithMachines extends SimpleMachineBrandProjection{
    List<SimpleMachineProjection> getMachines();
}
