package com.momo.common;

import static com.momo.Profile.TEST;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Disabled
@ActiveProfiles(TEST)
@ExtendWith(SpringExtension.class)
public class ServiceTest {

}
