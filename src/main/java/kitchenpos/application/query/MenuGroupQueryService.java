package kitchenpos.application.query;

import kitchenpos.dto.response.MenuGroupViewResponse;
import kitchenpos.exception.EntityNotExistsException;
import kitchenpos.repository.MenuGroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MenuGroupQueryService {
    private final MenuGroupRepository menuGroupRepository;

    public MenuGroupQueryService(final MenuGroupRepository menuGroupRepository) {
        this.menuGroupRepository = menuGroupRepository;
    }

    public List<MenuGroupViewResponse> list() {
        return menuGroupRepository.findAll()
                .stream()
                .map(MenuGroupViewResponse::of)
                .collect(Collectors.toList());
    }

    public MenuGroupViewResponse findById(Long id) {
        return MenuGroupViewResponse.of(menuGroupRepository.findById(id)
                .orElseThrow(EntityNotExistsException::new));
    }
}
