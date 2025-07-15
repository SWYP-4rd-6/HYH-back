package io.hyh.hyhapplication.pack.application;

import io.hyh.hyhapplication.common.exception.ErrorCode;
import io.hyh.hyhapplication.common.exception.HyhApplicationException;
import io.hyh.hyhapplication.pack.application.dto.PackingListRequest;
import io.hyh.hyhapplication.pack.application.dto.PackingListResponse;
import io.hyh.hyhapplication.pack.domain.CheckList;
import io.hyh.hyhapplication.pack.domain.CheckUserTest;
import io.hyh.hyhapplication.pack.infra.CheckUserTestJpaRepository;
import io.hyh.hyhapplication.pack.infra.PackingListJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PackingListQueryService {

    private final PackingListJpaRepository packingListJpaRepository;
    private final CheckUserTestJpaRepository checkUserTestJpaRepository;

    @Transactional(readOnly = true)
    public CheckUserTest getTestUser(String userId) {
        return checkUserTestJpaRepository.findById(userId)
                .orElseThrow(() -> new HyhApplicationException(ErrorCode.MEMBER_NOT_FOUND, String.format("%s not founded", userId)));
    }


    @Transactional(readOnly = true)
    public List<PackingListResponse> getPackingListByUserId(String userId) {
        CheckUserTest user = getTestUser(userId);

        return user.getCheckLists().stream().map(PackingListResponse::of).toList();
    }

    @Transactional
    public List<PackingListResponse> addPackingThing(String userId, List<PackingListRequest> things) {
        CheckUserTest user = getTestUser(userId);
        List<CheckList> lists = new ArrayList<>();

        for (PackingListRequest th : things) {
            int check = th.check();
            String thing = th.thing();

            if (user.getCheckLists().stream().anyMatch(u -> u.getThing().equals(thing))) {
                throw new HyhApplicationException(ErrorCode.DUPLICATED_CHECKLIST, String.format("%s is duplicated", thing));
            }

            lists.add(new CheckList(user, check, thing));
        }

        List<CheckList> result = packingListJpaRepository.saveAll(lists);
        return result.stream().map(PackingListResponse::of).toList();
    }

    @Transactional
    public String updateCheckStatus(String userId, String thing) {
        CheckUserTest user = getTestUser(userId);
        Optional<CheckList> list = user.getCheckLists().stream().filter(u -> u.getThing().equals(thing)).findFirst();

        if (list.isPresent()) {
            list.get().setCheck();
        } else
            throw new HyhApplicationException(ErrorCode.CHECKLIST_NOT_FOUND, String.format("%s is not founded", thing));

        packingListJpaRepository.save(list.get());

        return list.get().getCheck() == 0 ? String.format("%s : 상태(미완료) 변경 완료", thing)
                : String.format("%s : 상태(완료) 변경 완료", thing);
    }

    @Transactional
    public String deletePackingThing(String userId, String thing) {
        CheckUserTest user = getTestUser(userId);
        Optional<CheckList> list = user.getCheckLists().stream().filter(u -> u.getThing().equals(thing)).findFirst();

        if (list.isPresent()) {
            packingListJpaRepository.delete(list.get());
            user.getCheckLists().remove(list.get());
            if (user.getCheckLists().isEmpty()) return String.format("%s : 삭제 성공, 체크리스트를 새로 등록해보세요.", thing);
            else return String.format("%s : 삭제 성공", thing);
        } else
            throw new HyhApplicationException(ErrorCode.CHECKLIST_NOT_FOUND, String.format("%s is not founded", thing));
    }
}
