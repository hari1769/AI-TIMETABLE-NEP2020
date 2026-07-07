import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import tailwindcss from '@tailwindcss/vite'

export default defineConfig({
  plugins: [
    react(),
    tailwindcss(),
  ],
  server: {
    port: 5173,
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
      },
    },
  },
  build: {
    sourcemap: false,
    rollupOptions: {
      output: {
        manualChunks(id) {
          if (id.includes('node_modules')) {
            if (id.includes('react') || id.includes('react-dom')) return 'vendor';
            if (id.includes('react-router-dom')) return 'router';
            if (id.includes('@tanstack/react-query')) return 'query';
            if (id.includes('recharts')) return 'charts';
            if (id.includes('framer-motion')) return 'motion';
          }
        },
      },
    },
  },
})
