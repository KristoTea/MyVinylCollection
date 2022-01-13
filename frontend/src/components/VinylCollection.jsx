import React from "react";
import VinylCard from "./VinylCard";

function VinylCollection({ data, favVinyls, updateFunction }) {
  const [scrolled, setScrolled] = React.useState(false);

  return (
    <div
      style={{
        display: "flex",
        maxWidth: window.innerWidth * 0.83,
        height: "20rem",
        overflow: "scroll",
        position: "relative",
      }}
      onScroll={() => setScrolled(true)}
    >
      {!scrolled && data.length > 3 && (
        <div
          style={{
            height: "100%",
            width: "20px",
            position: "absolute",
            zIndex: 5,
            right: 0,
            backgroundImage:
              "radial-gradient(farthest-side at 100% 50%, rgba(0, 0, 0, 0.4) 0%, rgba(0, 0, 0, 0) 100%)",
          }}
        />
      )}
      {data.map((d) => (
        <VinylCard
          vinylData={d}
          favVinyls={favVinyls}
          updateFunction={updateFunction}
        />
      ))}
    </div>
  );
}

export default VinylCollection;
