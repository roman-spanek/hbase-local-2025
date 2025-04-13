package cz.tul.roman.spanek.rdb.rdb_hbase.controller;

import cz.tul.roman.spanek.rdb.rdb_hbase.service.HBaseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class HBaseController {

    private final HBaseService hBaseService;

    public HBaseController(HBaseService hBaseService) {
        this.hBaseService = hBaseService;
    }

    @PostMapping
    public String saveStudent(@RequestParam String id,
                              @RequestParam String name,
                              @RequestParam String age) {
        try {
            hBaseService.writeStudent(id, name, age);
            return "Student saved.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/{id}")
    public String getStudent(@PathVariable String id) {
        try {
            return hBaseService.readStudent(id);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}