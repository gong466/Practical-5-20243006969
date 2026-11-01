package com.example.practical5;

/**
 * Validates and normalises municipality names typed by the user.
 * Plain Java class: no Activity, no layout, no Android dependencies.
 */
public class TextProcessor {

    /** Intent extra key shared by MainActivity and Municipality. */
    public static final String EXTRA_MUNICIPALITY = "com.example.practical5.extra.MUNICIPALITY";

    /** Canonical (correctly capitalised) municipality names. */
    public static final String BEIJING = "Beijing";
    public static final String SHANGHAI = "Shanghai";
    public static final String CHONGQING = "Chongqing";
    public static final String TIANJIN = "Tianjin";

    /** Indexes into municipalities_array - must match the order in arrays.xml. */
    public static final int INDEX_BEIJING = 0;
    public static final int INDEX_SHANGHAI = 1;
    public static final int INDEX_CHONGQING = 2;
    public static final int INDEX_TIANJIN = 3;

    /**
     * @param municipality raw text typed by the user
     * @return the correctly capitalised municipality name, or null when the input
     *         is null, blank, or not one of the four supported municipalities
     */
    public String processMunicipality(String municipality) {
        if (municipality == null) {
            return null;
        }

        String trimmed = municipality.trim();
        if (trimmed.isEmpty()) {
            return null;
        }

        if (BEIJING.equalsIgnoreCase(trimmed)) {
            return BEIJING;
        }
        if (SHANGHAI.equalsIgnoreCase(trimmed)) {
            return SHANGHAI;
        }
        if (CHONGQING.equalsIgnoreCase(trimmed)) {
            return CHONGQING;
        }
        if (TIANJIN.equalsIgnoreCase(trimmed)) {
            return TIANJIN;
        }

        return null;
    }
}