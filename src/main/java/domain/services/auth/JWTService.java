package domain.services.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import domain.models.auth.Employee;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.Calendar;
import java.util.Date;

@Local
@Stateless
public class JWTService {
    private static final String secret = "secret";
    private static final String issuer = "callcenter";
    private static final int EXPIRE_MINUTES = 60;
    private static final Algorithm algorithm = Algorithm.HMAC256(secret);
    private JWTVerifier verifier;

    public JWTService() {
        verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .build();
    }

    public String createJWT(Employee e) throws JWTCreationException {
        return JWT.create()
                .withIssuer(issuer)
                .withClaim("id", e.getId())
                .withClaim("email", e.getEmail())
                .withClaim("roles", e.getCommaSeperatedRoles())
                .withExpiresAt(getExpireDate(EXPIRE_MINUTES))
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    public DecodedJWT verifyJWT(String token) throws JWTVerificationException {
        DecodedJWT decoded = verifier.verify(token);

        if (decoded.getClaim("id").isNull() || decoded.getClaim("email").isNull())
            throw new JWTVerificationException("Invalid claim data");

        return decoded;
    }

    public boolean isAdmin (String token) {
        try {
            DecodedJWT decodedJwt = verifyJWT(token);

            if (decodedJwt.getClaim("roles").isNull())
                return false;

            // check if has role admin
            return decodedJwt.getClaim("roles").asString().contains("admin");
        } catch (JWTVerificationException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private Date getExpireDate(int minutesToAdd) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MINUTE, minutesToAdd);
        return c.getTime();
    }
}
