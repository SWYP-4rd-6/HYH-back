package io.hyh.hyhapi.todo;

import io.hyh.hyhapi.common.dto.Response;
import io.hyh.hyhapplication.pack.application.PackingListQueryService;
import io.hyh.hyhapplication.pack.application.dto.PackingListRequest;
import io.hyh.hyhapplication.pack.application.dto.PackingListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/packing")
public class ThingsToPackRestController {

    private final PackingListQueryService packingListQueryService;

    @GetMapping("/{userId}")
    public Response<List<PackingListResponse>> getPackingListByUserId(@PathVariable String userId) {
        return Response.success(packingListQueryService.getPackingListByUserId(userId));
    }

    @PostMapping("/{userId}/things")
    public Response<List<PackingListResponse>> addPackingThing(@PathVariable String userId,
                                                               @RequestBody List<PackingListRequest> things) {
        return Response.success(packingListQueryService.addPackingThing(userId, things));
    }

    @DeleteMapping("/{userId}/Dthing")
    public Response<String> deletePackingThing(@PathVariable String userId,
                                               @RequestParam String thing) {
        return Response.success(packingListQueryService.deletePackingThing(userId, thing));
    }

    @PutMapping("/{userId}/check")
    public Response<String> updateCheckStatus(@PathVariable String userId,
                                              @RequestParam String thing) {
        return Response.success(packingListQueryService.updateCheckStatus(userId, thing));
    }
}
