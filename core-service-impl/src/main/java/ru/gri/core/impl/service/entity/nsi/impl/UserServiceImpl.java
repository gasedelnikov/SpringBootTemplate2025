package ru.gri.core.impl.service.entity.nsi.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gri.core.dao.model.nsi.UserEntity;
import ru.gri.core.dao.repository.entity.nsi.UserRepository;
import ru.gri.core.impl.mapper.BaseMapper;
import ru.gri.core.impl.service.entity.BaseServiceImpl;
import ru.gri.core.impl.service.entity.nsi.UserSearchService;
import ru.gri.core.model.dto.User;
import ru.gri.core.model.dto.out.nsi.UserOut;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserEntity, Long, User, UserOut> implements UserSearchService {

    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
// TODO use passwordEncoder.encode("new_password") to save password

    @Autowired
    public UserServiceImpl(BaseMapper<UserEntity, Long, User, UserOut> mapper,
                           UserRepository userRepository) {
        super(mapper, userRepository);
        this.userRepository = userRepository;
    }


    @Transactional(readOnly = true)
    public User findByLogin(String login) {
        return mapper.toInModel((userRepository).findByLogin(login));
    }

}
