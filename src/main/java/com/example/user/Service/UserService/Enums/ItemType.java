package com.example.user.Service.UserService.Enums;

public enum ItemType {
        BAKERY("Bakery"),
        COLD_DRINK("Cold Drink"),
        ALCOHOL("Alcohol"),
        HOT_DRINKS("Hot Drinks"),
        TOBACCO("Tobacco");

        private final String displayName;

        ItemType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public static ItemType fromDisplayName(String displayName) {
            for (ItemType itemType : ItemType.values()) {
                if (itemType.getDisplayName().equals(displayName)) {
                    return itemType;
                }
            }
            throw new IllegalArgumentException("No enum constant for display name: " + displayName);
        }
    }


