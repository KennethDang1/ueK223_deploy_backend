package com.example.demo.domain.blogpost;

// Enum representing different blog post categories.
public enum Category {
    FUN, SERIOUS, DISCUSSION, SPORTS, OUTDOOR_ACTIVITY, INDOOR_ACTIVITY;

    // Returns category
    public Category getCategory() {
        switch(this) {
            case FUN:
                return FUN;

            case SERIOUS:
                return SERIOUS;

            case DISCUSSION:
                return DISCUSSION;

            case SPORTS:
                return SPORTS;

            case OUTDOOR_ACTIVITY:
                return OUTDOOR_ACTIVITY;

            case INDOOR_ACTIVITY:
                return INDOOR_ACTIVITY;

            default:
                return null;
        }
    }
}
