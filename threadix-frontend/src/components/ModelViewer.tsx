import React, { Suspense, useState } from "react";
import { Canvas } from "@react-three/fiber";
import { OrbitControls, useGLTF } from "@react-three/drei";

const Model = ({ url }: { url: string }) => {
  const { scene } = useGLTF(url);
  return <primitive object={scene} />;
};

const ModelViewer = ({ url, blurred }: { url: string; blurred: boolean }) => {
  return (
    <div
      style={{ filter: blurred ? "blur(8px)" : "none", position: "relative" }}
    >
      <Canvas style={{ height: 300 }}>
        <ambientLight />
        <Suspense fallback={null}>
          <Model url={url} />
        </Suspense>
        <OrbitControls />
      </Canvas>
    </div>
  );
};

const PostModel = ({ url }: { url: string }) => {
  const [loaded, setLoaded] = useState(false);

  return (
    <div>
      <ModelViewer url={url} blurred={!loaded} />
      {!loaded && (
        <button
          onClick={() => setLoaded(true)}
          style={{ position: "absolute", top: 10, left: 10 }}
        >
          Load Model
        </button>
      )}
    </div>
  );
};

export default ModelViewer;
