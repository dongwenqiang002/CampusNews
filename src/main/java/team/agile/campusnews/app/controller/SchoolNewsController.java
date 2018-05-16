package team.agile.campusnews.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/school")
public class SchoolNewsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SchoolNewsController.class);
}
