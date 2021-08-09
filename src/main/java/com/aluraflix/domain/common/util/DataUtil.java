package com.aluraflix.domain.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface DataUtil {

    static String formatarDataHora(LocalDateTime dataHora) {
        return dataHora.format(DateTimeFormatter.ofPattern("dd//MM/yyyy HH:mm"));
    }
}
