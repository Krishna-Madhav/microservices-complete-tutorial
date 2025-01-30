package com.lcwd.rating.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("user_ratings")
public class Rating {

	 	@Id
	 	@JsonProperty("ratingId")
	    private String ratingId;

	    @JsonProperty("userId")
	    private String userId;

	    @JsonProperty("hotelId")
	    private String hotelId;

	    @JsonProperty("rating")
	    private int rating;

	    @JsonProperty("feedback")
	    private String feedback;

	    @Override
	    public String toString() {
	        return "Rating [ratingId=" + ratingId + ", userId=" + userId + ", hotelId=" + hotelId + ", rating=" + rating
	                + ", feedback=" + feedback + "]";
	    }
    
    
}
