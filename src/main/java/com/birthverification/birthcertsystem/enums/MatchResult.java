//package com.birthverification.birthcertsystem.enums;
//
//public enum MatchResult {
//    MATCHED,
//    NOT_MATCHED
//}


package com.birthverification.birthcertsystem.enums;

public enum MatchResult {
    MATCHED,          // All critical fields matched exactly
    PARTIAL_MATCH,    // Some fields matched but not all (with match score)
    NOT_MATCHED,      // No matching record found
    MANUAL_REVIEW,    // Requires officer review
    SYSTEM_ERROR      // Error occurred during matching
}