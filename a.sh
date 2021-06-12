rm -r out
rm -r libs

export PATH_TO_FX=/Users/luxia/dev/javafx-sdk-11.0.2/lib

javac --module-path $PATH_TO_FX --add-modules=javafx.controls,javafx.media,javafx.fxml -d out $(find src -name "*.java")

find $PATH_TO_FX/{javafx.base.jar,javafx.graphics.jar,javafx.controls.jar,javafx.media.jar} -exec unzip -nq {} -d out \;

cp $PATH_TO_FX/{libprism*.dylib,libjavafx*.dylib,libglass.dylib,libdecora_sse.dylib,libglib-lite.dylib,libgstreamer-lite.dylib,libjfx*.dylib} out

rm out/META-INF/MANIFEST.MF out/module-info.class

mkdir libs

cp -R assets/* out

jar --create --file=libs/Mario.jar --main-class=FXEntrance -C out .

java -jar libs/Mario.jar