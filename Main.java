package org.example;

// Import the required packages

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Map;


public class Main {
    public static void main(String[] args) {
        // Set your Cloudinary credentials

        Dotenv dotenv = Dotenv.load();
        Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
        cloudinary.config.secure = true;
        System.out.println(cloudinary.config.cloudName);

        try {
            // Upload the image
            Map params1 = ObjectUtils.asMap(
                "public_id", "coffee",
                "use_filename", true,
                "overwrite", true
            );

            System.out.println(cloudinary.uploader().upload("https://cloudinary-devs.github.io/cld-docs-assets/assets/images/coffee_cup.jpg", params1));

            // Get the asset details
            Map params2 = ObjectUtils.asMap(
                    "colors", true
            );

            System.out.println(cloudinary.api().resource("flower_sample", params2));

            // Create the image tag with the transformed image
            System.out.println(cloudinary.url().transformation(new Transformation()
                    .crop("pad")
                    .width(150)
                    .height(150)
                    .background("auto:predominant"))
                    .imageTag("flower_sample"));

            // The code above generates an HTML image tag similar to the following:
            //  <img src='https://res.cloudinary.com/demo/image/upload/b_auto:predominant,c_pad,h_150,w_150/flower_sample' height='150' width='150'/>

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
