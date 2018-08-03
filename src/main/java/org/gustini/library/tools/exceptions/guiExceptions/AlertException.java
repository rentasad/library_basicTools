/**
 *
 */
package org.gustini.library.tools.exceptions.guiExceptions;

import javafx.scene.control.Alert.AlertType;


/**
 * Gustini GmbH (2016)
 * Creation: 23.02.2016
 * Library
 * gustini.library.exceptions.guiExceptions
 *
 * @author Matthias Staud
 *
 *
 * Description:
 *
 */
public class AlertException extends Exception
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private AlertType alertType;
    private String messageTitle;
    private String messageHeader;

    /**
     * @param alertType
     * @param messageTitle
     * @param messageHeader
     */
    public AlertException(
                          AlertType alertType,
                          String messageTitle,
                          String messageHeader,
                          String messageContent)
    {
        super(messageContent);
        this.alertType = alertType;
        this.messageTitle = messageTitle;
        this.messageHeader = messageHeader;
    }

    /**
     * @param alertType
     * @param messageTitle
     * @param messageHeader
     */
    public AlertException(Throwable e,
                          AlertType alertType,
                          String messageTitle,
                          String messageHeader,
                          String messageContent)
    {
        super(messageContent, e);
        this.alertType = alertType;
        this.messageTitle = messageTitle;
        this.messageHeader = messageHeader;
    }

    /**
     *
     */
    public AlertException()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public AlertException(
                          String message)
    {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public AlertException(
                          Throwable cause)
    {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public AlertException(
                          String message,
                          Throwable cause)
    {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public AlertException(
                          String message,
                          Throwable cause,
                          boolean enableSuppression,
                          boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the alertType
     */
    public AlertType getAlertType()
    {
        return alertType;
    }

    /**
     * @return the messageTitle
     */
    public String getMessageTitle()
    {
        return messageTitle;
    }

    /**
     * @return the messageHeader
     */
    public String getMessageHeader()
    {
        return messageHeader;
    }

}
