package br.com.felipec91.domain.model.ticket.value_object;

import br.com.felipec91.domain.exception.InvalidAppointmentException;
import br.com.felipec91.domain.model.user.entity.UserModel;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.OffsetTime;
import java.util.Objects;

@Embeddable
public class Appointment {


    @Column(name = "appointment_date")
    private LocalDate appointmentDate;


    @Column(name = "beginning_at")
    private OffsetTime beginningAt;

    @Column(name = "ending_at")
    private OffsetTime endingAt;


    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_model_id")
    private UserModel accountable;

    public Appointment(){}

    private Appointment(LocalDate appointmentDate, OffsetTime beginningAt, OffsetTime endingAt, String description, UserModel accountable) {
        this.appointmentDate = appointmentDate;
        this.beginningAt = beginningAt;
        this.endingAt = endingAt;
        this.description = description;
        this.accountable = accountable;
    }

    public static Appointment create(LocalDate appointmentDate, OffsetTime beginningAt, OffsetTime endingAt, String description, UserModel accountable) {
        if (beginningAt.isAfter(endingAt) || beginningAt.equals(endingAt))
            throw new InvalidAppointmentException("Horario inicial deve ser anterior ao horario final");

        return new Appointment(appointmentDate, beginningAt, endingAt, description, accountable);
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public OffsetTime getBeginningAt() {
        return beginningAt;
    }

    public OffsetTime getEndingAt() {
        return endingAt;
    }

    public UserModel getAccountable() {
        return accountable;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(appointmentDate, that.appointmentDate) && Objects.equals(beginningAt, that.beginningAt) && Objects.equals(endingAt, that.endingAt) && Objects.equals(description, that.description) && Objects.equals(accountable, that.accountable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentDate, beginningAt, endingAt, description, accountable);
    }
}
