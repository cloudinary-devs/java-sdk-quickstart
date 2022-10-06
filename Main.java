package org.example;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

import java.util.Map;


public class Main {
    public static void main(String[] args) {
        // replace `cloudinary://my_key:my_secret@my_cloud_name` with your environment variable
        Cloudinary cloudinary = new Cloudinary("cloudinary://my_key:my_secret@my_cloud_name");
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
