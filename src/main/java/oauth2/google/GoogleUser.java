package oauth2.google;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class GoogleUser implements UserDetails {

	private final String id;
	private final String email;
	// private final boolean emailVerified;
	private final String userName;
	private final String givenName;
	private final String familyName;
	private final URI link;
	private final URI picture;
	private final String gender;
	private final Locale locale;
	private final String hostDomain;

	public GoogleUser(
			String id, String email, String userName, String givenName, String familyName,
			URI link, URI picture, String gender, Locale locale, String hostDomain) {
		super();
		this.id = id;
		this.email = email;
		this.userName = userName;
		this.givenName = givenName;
		this.familyName = familyName;
		this.link = link;
		this.picture = picture;
		this.gender = gender;
		this.locale = locale;
		this.hostDomain = hostDomain;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getGivenName() {
		return givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public URI getLink() {
		return link;
	}

	public URI getPicture() {
		return picture;
	}

	public String getGender() {
		return gender;
	}

	public Locale getLocale() {
		return locale;
	}

	public String getHostDomain() {
		return hostDomain;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GoogleUser [id=");
		builder.append(id);
		builder.append(", email=");
		builder.append(email);
		builder.append(", userName=");
		builder.append(userName);
		builder.append("]");
		return builder.toString();
	}

	public static GoogleUser fromUserInfoMap(Map<String, Object> map) {
		URI link;
		if (map.containsKey("link")) {
			link = getUri(map, "link");
		} else {
			link = getUri(map, "profile");
		}
		URI picture = getUri(map, "picture");
		return new GoogleUser(
				(String) (map.containsKey("id") ? map.get("id") : map.get("sub")),
				(String) map.get("email"),
				(String) map.get("name"),
				(String) map.get("given_name"),
				(String) map.get("family_name"),
				link,
				picture,
				(String) map.get("gender"),
				new Locale((String) map.get("locale")),
				(String) map.get("hd"));
	}

	private static URI getUri(Map<String, Object> map, String key) {
		URI uri = null;
		try {
			if (map.containsKey(key)) {
				uri = new URI((String) map.get(key));
			}
		} catch (URISyntaxException e) {}
		return uri;
	}

}
