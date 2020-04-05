package dev.tonini.trentinoconsegna.helpers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Helps generate and show {@link AlertDialog}
 */
public class DialogHelper {
    /**
     * Generate and show a {@link AlertDialog} with one button
     * @param context context where the dialog should be generated
     * @param title dialog's title
     * @param message dialog's message
     * @param buttonText text shown on the button
     * @return an instance of {@link AlertDialog}
     */
    public static AlertDialog show(Context context, String title, String message, String buttonText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(message).setCancelable(false).setPositiveButton(buttonText, null);
        return builder.show();
    }

    /**
     * Generate and show a {@link AlertDialog} with one button
     * @param context context where the dialog should be generated
     * @param title dialog's title
     * @param message dialog's message
     * @param buttonText text shown on the button
     * @return an instance of {@link AlertDialog}
     */
    public static AlertDialog show(Context context, int title, int message, int buttonText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(message).setCancelable(false).setPositiveButton(buttonText, null);
        return builder.show();
    }

    /**
     * Generate and show a {@link AlertDialog} with one button
     * @param context context where the dialog should be generated
     * @param title dialog's title
     * @param message dialog's message
     * @param buttonText text shown on the button
     * @return an instance of {@link AlertDialog}
     */
    public static AlertDialog show(Context context, String title, String message, String buttonText, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(message).setCancelable(false).setPositiveButton(buttonText, listener);
        return builder.show();
    }

    /**
     * Generate and show a {@link AlertDialog} with one button
     * @param context context where the dialog should be generated
     * @param title dialog's title
     * @param message dialog's message
     * @param buttonText text shown on the button
     * @return an instance of {@link AlertDialog}
     */
    public static AlertDialog show(Context context, int title, int message, int buttonText, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(message).setCancelable(false).setPositiveButton(buttonText, listener);
        return builder.show();
    }
}
