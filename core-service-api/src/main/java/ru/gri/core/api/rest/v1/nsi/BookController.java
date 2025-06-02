package ru.gri.core.api.rest.v1.nsi;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Min;
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
import ru.gri.core.impl.service.entity.nsi.BookService;
import ru.gri.core.model.dto.in.nsi.BookIn;
import ru.gri.core.model.dto.out.nsi.BookOut;
import ru.gri.core.model.rest.BaseResponse;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerScheme")
@RequestMapping("/template/v1/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<BaseResponse<BookOut>> getById(@PathVariable("id") Long id) {
        BookOut le = service.getById(id);
        return new ResponseEntity<>(new BaseResponse<>(le), HttpStatus.OK);
    }


    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<BaseResponse<BookOut>> create(@RequestBody BookIn value) {
        Long id = service.create(value);
        return new ResponseEntity<>(new BaseResponse<>(service.getById(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<BaseResponse<BookOut>> update(@RequestBody BookIn value) {
        service.update(value);
        return new ResponseEntity<>(new BaseResponse<>(service.getById(value.getId())), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<BaseResponse<Page<BookOut>>> getAll(
            @RequestParam(required = false) @Min(0) Integer page,
            @RequestParam(required = false) @Min(1) Integer limit,

            @SchemaSort(fields = "id, name, description")
            @RequestParam(required = false, name = "sort") List<String> sort) {
        Pageable pageable = ControllerUtils.getPageable(page, limit, sort);
        Page<BookOut> response = service.getPage(pageable);

        return new ResponseEntity<>(new BaseResponse<>(response), HttpStatus.OK);
    }


}
