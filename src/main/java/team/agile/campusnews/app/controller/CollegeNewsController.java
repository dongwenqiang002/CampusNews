package team.agile.campusnews.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/college")
public class CollegeNewsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CollegeNewsController.class);
}
