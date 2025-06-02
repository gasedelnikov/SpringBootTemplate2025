package ru.gri.core.api.rest.v1.nsi;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gri.core.api.rest.ControllerUtils;
import ru.gri.core.api.rest.annotation.SchemaSort;
import ru.gri.core.impl.service.entity.nsi.AuthorService;
import ru.gri.core.model.dto.in.nsi.AuthorIn;
import ru.gri.core.model.dto.out.nsi.AuthorOut;
import ru.gri.core.model.rest.BaseResponse;

import java.util.List;


@RestController
@SecurityRequirement(name = "bearerScheme")
@RequestMapping("/template/v1/authors")
public class AuthorController {

    private final AuthorService service;

    @Autowired
    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<BaseResponse<AuthorOut>> getById(@PathVariable("id") Long id) {
        AuthorOut le = service.getById(id);
        return new ResponseEntity<>(new BaseResponse<>(le), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<BaseResponse<AuthorOut>> create(@RequestBody AuthorIn value) {
        Long id = service.create(value);
        return new ResponseEntity<>(new BaseResponse<>(service.getById(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<BaseResponse<AuthorOut>> update(@RequestBody AuthorIn value) {
        service.update(value);
        return new ResponseEntity<>(new BaseResponse<>(service.getById(value.getId())), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<BaseResponse<Page<AuthorOut>>> getAll(
            @RequestParam(required = false) @Min(0) Integer page,
            @RequestParam(required = false) @Min(1) Integer limit,

            @SchemaSort(fields = "id, authorId, fio, description")
            @RequestParam(required = false, name = "sort") List<String> sort) {
        Pageable pageable = ControllerUtils.getPageable(page, limit, sort);
        Page<AuthorOut> response = service.getPage(pageable);

        return new ResponseEntity<>(new BaseResponse<>(response), HttpStatus.OK);
    }

}
